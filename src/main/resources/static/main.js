const config = {
    vapidPubKey: "BEK-t866raPMapcxRplp8mDKN-sIWIeSKgOqOnjqNRnftTqqLDk_Mz47AloRaGGQ6-2i1OL1Je5Wikc0rZjcUpw",
    serverUrl: "https://192.168.1.84:8443/backend/register",
  };


  async function subscribe(topic) {
      try{
        console.log(navigator.serviceWorker);
            swReg = await navigator.serviceWorker.register("/sw.js");

            console.log(swReg);

            const subscription = await swReg.pushManager.subscribe({
                userVisibleOnly: true,
                applicationServerKey: urlB64ToUint8Array(config.vapidPubKey),
            });

            console.log({topic: topic, subscription: JSON.stringify(subscription) });
            const id = new Date().getTime();
            const sub = JSON.stringify(subscription);

            await fetch(config.serverUrl, {
              method: "POST",
              // mode: "no-cors",
              headers: {
                  'Accept': 'application/json, text/plain, */*',
                  'Content-Type': 'application/json',
                  'Access-Control-Allow-Origin': '*'
              },
              body: JSON.stringify({ id: id, topic: topic, subscription: sub })
            });

        }catch(ex){
            console.log("error: ",ex);
        }
  }

  function urlB64ToUint8Array(base64String) {
    const padding = "=".repeat((4 - (base64String.length % 4)) % 4);
    const base64 = (base64String + padding)
      .replace(/\-/g, "+")
      .replace(/_/g, "/");
  
    const rawData = window.atob(base64);
    const outputArray = new Uint8Array(rawData.length);
  
    for (let i = 0; i < rawData.length; ++i) {
      outputArray[i] = rawData.charCodeAt(i);
    }
    return outputArray;
  }

  subscribe("tweets");