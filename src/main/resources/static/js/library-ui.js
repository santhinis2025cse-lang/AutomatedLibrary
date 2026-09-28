(() => {
    const reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;

    document.querySelectorAll("table").forEach((table, index) => {
        if (table.closest(".table-shell")) return;
        const shell = document.createElement("div");
        shell.className = "table-shell";
        shell.dataset.tableIndex = String(index + 1).padStart(2, "0");
        table.parentNode.insertBefore(shell, table);
        const existingSearch = table.closest(".card")?.querySelector(".search-row");
        if (!existingSearch) {
            const tools = document.createElement("div");
            tools.className = "table-tools";
            const label = document.createElement("label");
            label.className = "visually-hidden";
            label.htmlFor = `collection-search-${index}`;
            label.textContent = "Filter collection rows";
            const input = document.createElement("input");
            input.className = "table-search";
            input.type = "search";
            input.id = label.htmlFor;
            input.placeholder = "Filter this collection";
            input.setAttribute("aria-label", label.textContent);
            tools.append(label, input);
            shell.appendChild(tools);
            input.addEventListener("input", () => {
                const query = input.value.trim().toLocaleLowerCase();
                table.querySelectorAll("tbody tr").forEach((row) => {
                    if (row.querySelector("td.empty")) return;
                    row.hidden = !row.innerText.toLocaleLowerCase().includes(query);
                });
            });
        }
        shell.appendChild(table);
    });

    document.querySelectorAll(".table-search").forEach((input) => {
        const table = document.querySelector(input.dataset.table || "table");
        if (!table) return;
        input.addEventListener("input", () => {
            const query = input.value.trim().toLocaleLowerCase();
            table.querySelectorAll("tbody tr").forEach((row) => {
                if (row.querySelector("td.empty")) return;
                row.hidden = !row.innerText.toLocaleLowerCase().includes(query);
            });
        });
    });

    if (reducedMotion || !window.matchMedia("(hover: hover) and (pointer: fine)").matches) return;

    document.querySelectorAll("[data-parallax]").forEach((element) => {
        const strength = Number(element.dataset.parallax) || 0.2;
        let frame = 0;
        element.addEventListener("pointermove", (event) => {
            const bounds = element.getBoundingClientRect();
            const x = (event.clientX - bounds.left) / bounds.width - 0.5;
            const y = (event.clientY - bounds.top) / bounds.height - 0.5;
            cancelAnimationFrame(frame);
            frame = requestAnimationFrame(() => {
                element.style.setProperty("--pointer-x", `${x * 12 * strength}px`);
                element.style.setProperty("--pointer-y", `${y * 12 * strength}px`);
            });
        });
        element.addEventListener("pointerleave", () => {
            cancelAnimationFrame(frame);
            element.style.setProperty("--pointer-x", "0px");
            element.style.setProperty("--pointer-y", "0px");
        });
    });

    const revealItems = document.querySelectorAll(".reveal-on-scroll");
    if ("IntersectionObserver" in window && revealItems.length) {
        const observer = new IntersectionObserver((entries) => {
            entries.forEach((entry) => {
                if (entry.isIntersecting) {
                    entry.target.classList.add("is-visible");
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.12 });
        revealItems.forEach((item) => observer.observe(item));
    } else {
        revealItems.forEach((item) => item.classList.add("is-visible"));
    }
})();
