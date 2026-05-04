console.log("SCRIPT LOADED SUCCESSFULLY");
const BASE_URL = "http://localhost:8085";

let cartCount = parseInt(localStorage.getItem("cartCount")) || 0;
let total = parseInt(localStorage.getItem("cartTotal")) || 0;

// ---------------- REGISTER ----------------
async function registerUser() {
    const username = document.getElementById("regUsername").value.trim();
    const password = document.getElementById("regPassword").value.trim();

    try {
        const response = await fetch(`${BASE_URL}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
        });

        const text = await response.text();
        console.log("REGISTER RESPONSE:", text);

        if (text.includes("Successful")) {
            alert("Registration Successful");
            window.location.href = "login.html";
        } else {
            alert(text);
        }

    } catch (error) {
        console.error("REGISTER ERROR:", error);
        alert("Registration failed");
    }

    return false;
}

// ---------------- LOGIN ----------------
async function validateLogin() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    try {
        const response = await fetch(`${BASE_URL}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
        });

        const text = await response.text();
        console.log("LOGIN RESPONSE:", text);

        if (text.includes("SUCCESS")) {
            alert("Login Successful");
            window.location.href = "products.html";
        } else {
            alert("Invalid username or password");
        }

    } catch (error) {
        console.error("LOGIN ERROR:", error);
        alert("Login failed");
    }

    return false;
}

// ---------------- ADD TO CART ----------------
function addToCart(price) {
    cartCount++;
    total += price;

    localStorage.setItem("cartCount", cartCount);
    localStorage.setItem("cartTotal", total);

    updateCartUI();
    alert("Added to cart successfully");
}

// ---------------- UPDATE CART ----------------
function updateCartUI() {
    const countEls = document.querySelectorAll("#cart-count");
    const totalEls = document.querySelectorAll("#cart-total");

    countEls.forEach(el => el.innerText = cartCount);
    totalEls.forEach(el => el.innerText = total);
}

// ---------------- PLACE ORDER ----------------
async function placeOrder() {
    try {
        const response = await fetch(`${BASE_URL}/order`, {
            method: "POST"
        });

        const text = await response.text();
        alert(text);

        localStorage.removeItem("cartCount");
        localStorage.removeItem("cartTotal");

        cartCount = 0;
        total = 0;

        updateCartUI();
        window.location.href = "products.html";

    } catch (error) {
        console.error("ORDER ERROR:", error);
        alert("Order failed");
    }
}

// ---------------- PAGE LOAD ----------------
window.onload = function () {
    updateCartUI();
};
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("registerForm");

    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault();
            registerUser();
        });
    }
});
document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    if (loginForm) {
        loginForm.addEventListener("submit", function (e) {
            e.preventDefault();
            validateLogin();
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", function (e) {
            e.preventDefault();
            registerUser();
        });
    }
});