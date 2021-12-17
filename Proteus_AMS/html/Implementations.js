var getAllURL = "https://8j7a137nnf.execute-api.us-east-2.amazonaws.com/beta/getAllImplementations";
var getURL = "https://8j7a137nnf.execute-api.us-east-2.amazonaws.com/beta/getImplementation";

var data = {};
data["algorithmID"] = sessionStorage.getItem("algorithmId");
data["requestedBy"] = sessionStorage.getItem("username");
var xhr = new XMLHttpRequest();
xhr.open("POST", getAllURL, true);
xhr.send(JSON.stringify(data)); // This will process results and update HTML as appropriate. 
xhr.onloadend = function() {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
        if (xhr.status == 200) {
            var select = document.getElementById("Implementation");
            var js = JSON.parse(xhr.responseText);
            var res = js["implementations"];
            for (var i = 0; i < res.length; i++) {
                var option = document.createElement("option");
                option.value = res[i].implementationID;
                option.text = res[i].implementationLanguage;
                select.appendChild(option);
            }
            console.log("Loaded all Implementations!");
        } else if (xhr.status == 400) {
            alert("unable to process request");
        }
    } else {
        alert("unable to process request");
    }
};

function handleClick(e) {
    var selected = document.getElementById("Implementation");
    var implementationID = selected.value; 

    if (!(implementationID === "")) {
        var msg = document.getElementById("msg");
        sessionStorage.setItem("implementationID", implementationID);
        msg.innerHTML = "Successfully retrieved the implementation";

        var requestedBy = sessionStorage.getItem("username");
        var data = {};
        data["implementationID"] = implementationID;
        data["requestedBy"] = requestedBy;
        var js = JSON.stringify(data);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", getURL, true);

        // send the collected data as JSON
        xhr.send(js);

        // This will process results and update HTML as appropriate. 
        xhr.onloadend = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var js = JSON.parse(xhr.responseText);
                    var res = js["implementation"];
                    sessionStorage.setItem("implementationURL", res.implementationURL);
                    sessionStorage.setItem("implementationDetails", res.implemnetationDetails);
                    sessionStorage.setItem("implementationLanguage", res.implementationLanguage);
                    // sessionStorage.setItem("algorithmName", res.algorithmName);
                    
                    window.location.href = "Implementation.html";
                } else if (xhr.status == 400) {
                    alert("unable to process request");
                }
            } else {
                alert("unable to process request");
            }
        };
    }
}