(() => {
    const hero = document.querySelector(".hero");
    const canvas = document.querySelector("#library-network");
    if (!hero || !canvas) return;

    const reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
    const context = canvas.getContext("2d", { alpha: true });
    const nodes = [
        { x: 0.12, y: 0.30, label: "MEMBERS", type: 0 },
        { x: 0.20, y: 0.70, label: "CATALOG", type: 1 },
        { x: 0.29, y: 0.43, label: "EBOOK", type: 2 },
        { x: 0.38, y: 0.20, label: "COLLECTION", type: 1 },
        { x: 0.45, y: 0.78, label: "LICENSE", type: 3 },
        { x: 0.55, y: 0.35, label: "LIBRARY", type: 0 },
        { x: 0.63, y: 0.68, label: "ACCESS", type: 3 },
        { x: 0.72, y: 0.22, label: "PUBLISHER", type: 2 },
        { x: 0.82, y: 0.53, label: "DISTRICT", type: 0 },
        { x: 0.91, y: 0.31, label: "ORDERS", type: 1 },
        { x: 0.10, y: 0.82, label: "PUBLIC", type: 0 },
        { x: 0.34, y: 0.88, label: "ISBN", type: 1 },
        { x: 0.69, y: 0.88, label: "TERMS", type: 3 },
        { x: 0.89, y: 0.78, label: "RECORDS", type: 1 }
    ];
    const edges = [[0, 2], [0, 10], [1, 2], [1, 4], [2, 3], [2, 5], [3, 5], [4, 5], [4, 6], [5, 6], [5, 7], [6, 8], [6, 12], [7, 8], [7, 9], [8, 9], [8, 13], [10, 11], [11, 4], [12, 13]];
    const colors = ["#2563EB", "#7C3AED", "#059669", "#0891B2"];
    let width = 0;
    let height = 0;
    let pixelRatio = 1;
    let scrollTarget = 0;
    let scrollProgress = 0;
    let pointerX = 0;
    let pointerY = 0;
    let time = 0;
    let visible = true;
    let animationFrame = 0;

    function resizeCanvas() {
        const bounds = hero.getBoundingClientRect();
        width = bounds.width;
        height = bounds.height;
        pixelRatio = Math.min(window.devicePixelRatio || 1, 2);
        canvas.width = Math.round(width * pixelRatio);
        canvas.height = Math.round(height * pixelRatio);
        canvas.style.width = `${width}px`;
        canvas.style.height = `${height}px`;
        context.setTransform(pixelRatio, 0, 0, pixelRatio, 0, 0);
        renderNetwork();
    }

    function updateScrollProgress() {
        const maxScroll = Math.max(1, document.documentElement.scrollHeight - window.innerHeight);
        scrollTarget = Math.min(1, Math.max(0, window.scrollY / maxScroll));
    }

    function renderNetwork() {
        if (!context || !width || !height) return;
        context.clearRect(0, 0, width, height);
        const progress = scrollProgress;
        const spread = 0.88 + progress * 0.12;
        const visibility = 0.16 + progress * 0.54;
        const depth = 0.76 + progress * 0.24;
        const points = nodes.map((node, index) => {
            const drift = reducedMotion ? 0 : Math.sin(time * 0.00035 + index * 1.6) * (3 + progress * 5);
            const px = width * (0.5 + (node.x - 0.5) * spread) + pointerX * 0.15;
            const py = height * (0.5 + (node.y - 0.5) * spread) + drift + pointerY * 0.12;
            return { ...node, px, py, drift };
        });

        edges.forEach(([from, to], index) => {
            if (progress < 0.08 && index % 2 === 0) return;
            const first = points[from];
            const second = points[to];
            const pulse = reducedMotion ? 0 : (Math.sin(time * 0.0005 + index * 0.9) + 1) * 0.5;
            context.beginPath();
            context.moveTo(first.px, first.py);
            context.lineTo(second.px, second.py);
            context.strokeStyle = `rgba(37, 99, 235, ${visibility * (0.12 + pulse * 0.1)})`;
            context.lineWidth = 0.7 + progress * 0.35;
            context.stroke();
            if (progress > 0.42 && pulse > 0.82) {
                const px = first.px + (second.px - first.px) * pulse;
                const py = first.py + (second.py - first.py) * pulse;
                context.beginPath();
                context.arc(px, py, 1.6, 0, Math.PI * 2);
                context.fillStyle = `rgba(5, 150, 105, ${visibility * 0.8})`;
                context.fill();
            }
        });

        points.forEach((node, index) => {
            const pulse = reducedMotion ? 0 : (Math.sin(time * 0.00045 + index * 1.8) + 1) * 0.5;
            const radius = (2 + node.type * 0.22 + pulse * 0.8) * depth;
            const color = colors[node.type];
            context.beginPath();
            context.arc(node.px, node.py, radius * 3.2, 0, Math.PI * 2);
            context.fillStyle = `${color}${Math.round(visibility * (0.05 + pulse * 0.04) * 255).toString(16).padStart(2, "0")}`;
            context.fill();
            context.beginPath();
            context.arc(node.px, node.py, radius, 0, Math.PI * 2);
            context.fillStyle = color;
            context.globalAlpha = visibility + pulse * 0.15;
            context.fill();
            context.globalAlpha = 1;
            if (progress > 0.32 && index % 2 === 0) {
                context.font = "9px ui-monospace, SFMono-Regular, Consolas, monospace";
                context.fillStyle = `rgba(100, 116, 139, ${Math.min(0.72, progress * 0.7)})`;
                context.fillText(node.label, node.px + 9, node.py - 7);
            }
        });
    }

    function animateNetwork(timestamp) {
        animationFrame = 0;
        if (!visible || document.hidden) return;
        time = timestamp;
        scrollProgress += (scrollTarget - scrollProgress) * 0.055;
        if (Math.abs(scrollProgress - scrollTarget) < 0.001) scrollProgress = scrollTarget;
        renderNetwork();
        if (!reducedMotion) animationFrame = requestAnimationFrame(animateNetwork);
    }

    function startNetwork() {
        if (!animationFrame && visible && !document.hidden && !reducedMotion) animationFrame = requestAnimationFrame(animateNetwork);
        if (reducedMotion) renderNetwork();
    }

    updateScrollProgress();
    resizeCanvas();
    startNetwork();
    window.addEventListener("scroll", updateScrollProgress, { passive: true });
    window.addEventListener("resize", () => {
        resizeCanvas();
        measureFocusTargets();
    }, { passive: true });
    window.addEventListener("orientationchange", resizeCanvas, { passive: true });
    document.addEventListener("visibilitychange", startNetwork);
    if ("IntersectionObserver" in window) {
        new IntersectionObserver(([entry]) => {
            visible = entry.isIntersecting;
            if (!visible) {
                cancelAnimationFrame(animationFrame);
                animationFrame = 0;
            }
            else startNetwork();
        }, { threshold: 0.01 }).observe(hero);
    }

    if (!reducedMotion && window.matchMedia("(hover: hover) and (pointer: fine)").matches) {
        hero.addEventListener("pointermove", (event) => {
            const bounds = hero.getBoundingClientRect();
            pointerX = (event.clientX - bounds.left - bounds.width / 2) / bounds.width * 12;
            pointerY = (event.clientY - bounds.top - bounds.height / 2) / bounds.height * 12;
        }, { passive: true });
        hero.addEventListener("pointerleave", () => { pointerX = 0; pointerY = 0; }, { passive: true });
    }

    const focusWords = [...hero.querySelectorAll("[data-focus-target]")];
    const focusFrame = hero.querySelector("#focus-frame");
    let focusRects = [];
    let focusIndex = 0;
    let focusX = 0;
    let focusY = 0;
    let focusWidth = 0;
    let focusHeight = 0;
    let focusPauseUntil = 0;
    let focusAnimation = 0;

    function measureFocusTargets() {
        const heroBounds = hero.getBoundingClientRect();
        focusRects = focusWords.map((word) => {
            const bounds = word.getBoundingClientRect();
            return {
                x: bounds.left - heroBounds.left - 9,
                y: bounds.top - heroBounds.top - 5,
                width: bounds.width + 18,
                height: bounds.height + 10
            };
        });
    }

    function animateFocus(timestamp) {
        const destination = focusRects[focusIndex];
        if (!destination || reducedMotion) return;
        const stiffness = 0.13;
        focusX += (destination.x - focusX) * stiffness;
        focusY += (destination.y - focusY) * stiffness;
        focusWidth += (destination.width - focusWidth) * stiffness;
        focusHeight += (destination.height - focusHeight) * stiffness;
        focusFrame.style.left = `${focusX}px`;
        focusFrame.style.top = `${focusY}px`;
        focusFrame.style.width = `${focusWidth}px`;
        focusFrame.style.height = `${focusHeight}px`;

        const settled = Math.abs(focusX - destination.x) < 1.4 && Math.abs(focusY - destination.y) < 1.4;
        if (settled && focusPauseUntil === 0) {
            focusWords[focusIndex].classList.add("is-focused");
            focusPauseUntil = timestamp + 420;
        } else if (focusPauseUntil && timestamp > focusPauseUntil) {
            focusIndex += 1;
            focusPauseUntil = 0;
            if (focusIndex >= focusWords.length) {
                focusFrame.classList.add("is-complete");
                return;
            }
        }
        focusAnimation = requestAnimationFrame(animateFocus);
    }

    function beginFocusReveal() {
        if (!focusWords.length || !focusFrame) return;
        measureFocusTargets();
        if (reducedMotion) {
            focusWords.forEach((word) => word.classList.add("is-focused"));
            focusFrame.hidden = true;
            return;
        }
        document.documentElement.classList.add("focus-ready");
        const first = focusRects[0];
        focusX = first.x;
        focusY = first.y;
        focusWidth = first.width;
        focusHeight = first.height;
        focusFrame.hidden = false;
        focusAnimation = requestAnimationFrame(animateFocus);
    }

    if (document.fonts && document.fonts.ready) document.fonts.ready.then(beginFocusReveal);
    else beginFocusReveal();
    window.addEventListener("resize", () => {
        cancelAnimationFrame(focusAnimation);
        measureFocusTargets();
        if (focusIndex < focusWords.length && !reducedMotion) focusAnimation = requestAnimationFrame(animateFocus);
    }, { passive: true });
    if ("ResizeObserver" in window) new ResizeObserver(measureFocusTargets).observe(hero);
})();
