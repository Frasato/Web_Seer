document.addEventListener("DOMContentLoaded", () => {
  const loginBtn = document.getElementById("loginBtn");

  loginBtn.addEventListener("click", async () => {
    const user = document.getElementById("user").value;
    const password = document.getElementById("password").value;

    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name: user,
          password: password
        })
      });

      if (response.status === 200) {
        const id = await response.text();
        
        await chrome.storage.sync.set({ 
          loggedIn: true, 
          userId: id,
          userName: user 
        });

        chrome.runtime.sendMessage({ 
          type: "LOGIN_DONE", 
          userId: id 
        });

        alert("Login realizado com sucesso!");
        window.close();
      } else {
        alert("Usuário ou senha inválidos!");
      }
    } catch (error) {
      console.error("Erro no login:", error);
      alert("Erro ao conectar com o servidor! " + error);
    }
  });
});