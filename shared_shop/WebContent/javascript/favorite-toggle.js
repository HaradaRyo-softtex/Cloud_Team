document.addEventListener("DOMContentLoaded", function() {
  const button = document.getElementById("favoriteButton");
  if (!button) return;

  const img = document.getElementById("favoriteImg");

  button.addEventListener("click", function() {
    const isLoggedIn = button.getAttribute("data-logged-in") === "true";
    const itemId = button.getAttribute("data-item-id");

    if (!isLoggedIn) {
      window.location.href = contextPath + "/login";
      return;
    }

    fetch(contextPath + "/favorite/toggle", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: "item_id=" + encodeURIComponent(itemId)
    })
    .then(response => response.json())
    .then(data => {
      if (data.status === "added") {
        img.src = contextPath + "/img/heart_pink.png";
      } else if (data.status === "removed") {
        img.src = contextPath + "/img/heart.png";
      }
    });
  });
});
