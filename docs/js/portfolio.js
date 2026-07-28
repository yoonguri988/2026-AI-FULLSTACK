document.addEventListener("DOMContentLoaded", function () {
  // ===== 1) 스크롤 reveal: 섹션이 화면에 들어오면 fade + slide-up =====
  var revealSections = document.querySelectorAll(".reveal-section");
  if ("IntersectionObserver" in window) {
    var io = new IntersectionObserver(
      function (entries) {
        entries.forEach(function (entry) {
          if (entry.isIntersecting) {
            entry.target.classList.add("is-visible");
            io.unobserve(entry.target);
          }
        });
      },
      { threshold: 0.15 },
    );
    revealSections.forEach(function (el) {
      io.observe(el);
    });
  } else {
    // IntersectionObserver 미지원 브라우저 대비: 그냥 바로 보이게 처리
    revealSections.forEach(function (el) {
      el.classList.add("is-visible");
    });
  }

  // ===== 2) ERP 프로젝트 v1 / v2 / v3 탭 전환 =====
  var tabButtons = document.querySelectorAll(".version-tab");
  var tabPanels = document.querySelectorAll(".version-panel");

  tabButtons.forEach(function (btn) {
    btn.addEventListener("click", function () {
      var target = btn.getAttribute("data-target");

      tabButtons.forEach(function (b) {
        b.classList.remove("active");
      });
      btn.classList.add("active");

      tabPanels.forEach(function (panel) {
        panel.classList.toggle(
          "active",
          panel.getAttribute("data-version") === target,
        );
      });
    });
  });

  // ===== 3) 개인 프로젝트 카드 마우스 틸트 효과 =====
  var tiltCards = document.querySelectorAll(".tilt-card");
  tiltCards.forEach(function (card) {
    card.addEventListener("mousemove", function (e) {
      var rect = card.getBoundingClientRect();
      var x = (e.clientX - rect.left) / rect.width - 0.5;
      var y = (e.clientY - rect.top) / rect.height - 0.5;
      var rotateX = (-y * 6).toFixed(2);
      var rotateY = (x * 6).toFixed(2);
      card.style.transform =
        "perspective(600px) rotateX(" +
        rotateX +
        "deg) rotateY(" +
        rotateY +
        "deg)";
    });
    card.addEventListener("mouseleave", function () {
      card.style.transform = "perspective(600px) rotateX(0deg) rotateY(0deg)";
    });
  });
});
