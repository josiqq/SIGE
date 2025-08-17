$(document).ready(function() {
        // Obtenemos las pestañas y contenido
        const tabs = $("#nav-tab .nav-link");
        const tabContent = $("#nav-tabContent .tab-pane");

        // Inicialmente mostramos la pestaña activa
        const activeTabIndex = tabs.index($(".nav-link.active"));
        showTab(activeTabIndex);

        // Manejador de eventos táctiles para el deslizamiento
        let initialX = null;
        $(document).on("touchstart", function(event) {
            const touch = event.originalEvent.touches[0];
            initialX = touch.clientX;
        });

        $(document).on("touchend", function(event) {
            if (initialX === null) return;

            const touch = event.originalEvent.changedTouches[0];
            const finalX = touch.clientX;
            const deltaX = finalX - initialX;

            if (deltaX > 50) {
                // Deslizamiento hacia la izquierda, cambiamos a la pestaña anterior
                const activeIndex = tabs.index($(".nav-link.active"));
                showTab(activeIndex - 1);
            } else if (deltaX < -50) {
                // Deslizamiento hacia la derecha, cambiamos a la siguiente pestaña
                const activeIndex = tabs.index($(".nav-link.active"));
                showTab(activeIndex + 1);
            }

            initialX = null;
        });

        // Función para mostrar la pestaña seleccionada
        function showTab(index) {
            if (index >= 0 && index < tabs.length) {
                tabs.removeClass("active");
                tabs.eq(index).addClass("active");

                tabContent.removeClass("show active");
                tabContent.eq(index).addClass("show active");
            }
        }
    });