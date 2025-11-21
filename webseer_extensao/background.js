chrome.runtime.onStartup.addListener(() => {
  inicializarSeLogado();
});

chrome.runtime.onInstalled.addListener(() => {
  inicializarSeLogado();
});

chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
  if (message.type === "LOGIN_DONE") {
    console.log("Usuário logado com ID:", message.userId);
    inicializarLogica();
  }
});

async function inicializarSeLogado() {
  const { loggedIn } = await chrome.storage.sync.get("loggedIn");
  if (loggedIn) {
    console.log("Usuário já estava logado, iniciando lógica...");
    inicializarLogica();
  }
}

function inicializarLogica() {
  console.log("Monitoramento de navegação ativado!");

  chrome.tabs.onUpdated.addListener(async (tabId, changeInfo, tab) => {
    if (changeInfo.status === "complete" && tab.url) {
      if (tab.url.startsWith("chrome://") || tab.url.startsWith("chrome-extension://")) {
        return;
      }

      console.log("Página visitada:", tab.url);
      await enviarUrlParaAPI(tab.url);
    }
  });

  chrome.tabs.onActivated.addListener(async (activeInfo) => {
    const tab = await chrome.tabs.get(activeInfo.tabId);
    if (tab.url && !tab.url.startsWith("chrome://") && !tab.url.startsWith("chrome-extension://")) {
      console.log("Aba ativada:", tab.url);
      await enviarUrlParaAPI(tab.url);
    }
  });
}

async function enviarUrlParaAPI(url) {
  try {
    const { userId } = await chrome.storage.sync.get("userId");

    if (!userId) {
      console.error("ID do usuário não encontrado!");
      return;
    }

    const response = await fetch("http://localhost:8080/url", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        userId: userId,
        url: url
      })
    });

    if (response.ok) {
      console.log("URL enviada com sucesso:", url);
    } else {
      console.error("Erro ao enviar URL:", response.status);
    }
  } catch (error) {
    console.error("Erro ao conectar com a API:", error);
  }
}