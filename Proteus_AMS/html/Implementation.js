var removeURL = "https://8j7a137nnf.execute-api.us-east-2.amazonaws.com/beta/removeImplementation";
var downloadURL = "https://8j7a137nnf.execute-api.us-east-2.amazonaws.com/beta/downloadImplementation";

var implementationID = sessionStorage.getItem("implementationID");
var requestedBy = sessionStorage.getItem("username");
var blob = null;
var data = {};
data["implementationID"] = implementationID;
data["requestedBy"] = requestedBy;
var js = JSON.stringify(data);
var xhr = new XMLHttpRequest();
xhr.open("POST", downloadURL, true);

// send the collected data as JSON
xhr.send(js);

// This will process results and update HTML as appropriate. 
xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
        if (xhr.status == 200) {
            var js = JSON.parse(xhr.responseText);
            var message = js["Message"];
            var statusCode = js["statusCode"];

            if (statusCode === 200) {
                var xhr2 = new XMLHttpRequest();
                xhr2.open("GET", message, true);
                xhr2.send();

                xhr2.onloadend = function () {
                    if (xhr2.readyState == XMLHttpRequest.DONE) {
                        if (xhr2.status == 200) {
                            // Create a Blob object
                            blob = new Blob([xhr2.responseText], { type: 'text/plain' });
                            var code = document.getElementById("implementationCode");
                            var codeText = "";
                            blob.text().then(text => { 
                                codeText = text.replaceAll("<", "&#60");
                                codeText2 = codeText.replaceAll(">", "&#62");
                                code.innerHTML = codeText2;
                            });
                        } else if (xhr2.status == 400) {
                            alert("Unable to process request!");
                        }
                    } else {
                        alert("unable to download implementation file");
                    }
                };
            } else if (xhr2.status == 400) {
                alert("unable to process request");
            }
        } else {
            alert("unable to retrieve implementation file");
        }
    }
};

function processResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    var js = JSON.parse(result);

    var statusCode = js["statusCode"];

    var msg = document.getElementById("msg");

    if (statusCode === 200) {
        sessionStorage.removeItem("implementationID");
        window.location.href = "GetAlgorithmMainPage.html";
    } else {
        msg.innerHTML = js["Message"];
    }
}

function handlePost(data) {
    data["requestedBy"] = requestedBy;
    var js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", removeURL, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                processResponse(xhr.responseText);
            } else if (xhr.status == 400) {
                alert("unable to process request");
            }
        } else {
            processResponse("N/A");
        }
    };
}

function removeImplementation(e) {
    if (!(implementationID === "")) {
        var data = {};
        data["implementationID"] = implementationID;
        handlePost(data)                
    } else {
        var msg = document.getElementById("msg");
        msg.innerHTML = "An error occurred.";
    }
}

function downloadImplementation(e) {
    const download = (path, filename) => {
        // Create a new link
        const anchor = document.createElement('a');
        anchor.href = path;
        anchor.download = filename;

        // Append to the DOM
        document.body.appendChild(anchor);

        // Trigger `click` event
        anchor.click();

        // Remove element from DOM
        document.body.removeChild(anchor);
    }; 

    // Create a Blob object
    const filename = sessionStorage.getItem("implementationURL");

    // Create an object URL
    const url = URL.createObjectURL(blob);

    // Download file
    download(url, filename);

    // Release the object URL
    URL.revokeObjectURL(url); 
}
