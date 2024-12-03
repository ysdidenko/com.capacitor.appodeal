import { AppodealPlugin } from 'capacitor-appodeal';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    AppodealPlugin.echo({ value: inputValue })
}
