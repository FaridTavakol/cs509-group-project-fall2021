var add_url = "https://8j7a137nnf.execute-api.us-east-2.amazonaws.com/beta/addImplementation";
function notEmpty(algorithmID, language, url) {
    return !(algorithmID === "" || language === "" || url === "");
}

function processResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var message = js["Message"];
    var statusCode = js["statusCode"];

    if (statusCode == 200) {
        sessionStorage.setItem("implementationID", message);
        window.location.href = "Implementation.html";
    } else {
        // Update computation result
        var msg = document.getElementById("Msg");
        msg.innerHTML = js["Message"];
    }

}

function handleClick(e) {
    var details = document.getElementById('details').value;
    var url = document.getElementById('theFile').value.split(/(\\|\/)/g).pop();
    var algorithmID = sessionStorage.getItem("algorithmId");
    var extRE = /(?:\.([^.]+))?$/;
    var extension = extRE.exec(url)[1];
    var language = "Other";
    var requestedBy = sessionStorage.getItem("username");
    
    if (extension === "java") {
        language = "Java";
    }
    if (extension === "py") {
        language = "Python";
    }
    if (extension === "c") {
        language = "C";
    }
    if (extension === "CPP") {
        language = "C++";
    }
    if (extension === "js") {
        language = "JavaScript";
    }
    if (extension === "html") {
        language = "HTML";
    }
    
    var re = /(\.java|\.html|\.h|\.H|\.hxx|\.Hxx|\.HXX|\.c|\.CPP|\.cxx|\.CXX|\.pl|\.PL|\.sbl|\.sh|\.py|\.cs|\.vb|\.js|\.ts|\.tsx|\.jsx|\.php|\.phps|\.swift|\.sql|\.rb|\.m|\.asm|\.s|\.pl|\.PL|\.mat|\.r)$/i;
    
    if (notEmpty(algorithmID, language, url) && re.exec(url)) {
        console.log("Valid Input.")
        setTimeout(function() {
            var data = {};
            data["algorithmID"] = algorithmID;
            data["language"] = language;
            data["details"] = details;
            data["url"] = url;
            data["requestedBy"] = requestedBy;
            sessionStorage.setItem("implementationURL", url);
            sessionStorage.setItem("implementationDetails", details);
            sessionStorage.setItem("implementationLanguage", language);
            var js = JSON.stringify(data);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", add_url, true);
            // send the collected data as JSON
            xhr.send(js);
            // This will process results and update HTML as appropriate. 
            xhr.onloadend = function() {
                if (xhr.readyState == XMLHttpRequest.DONE) {
                    if (xhr.status == 200) {
                        console.log("XHR:" + xhr.responseText);
                        processResponse(xhr.responseText);
                    } else if (xhr.status == 400) {
                        console.log("UNABLE TO PROCESS REQUEST");
                        alert("Unable to process request");
                    }
                } else {
                    processResponse("N/A");
                }
            };
        }, 100);
    } else {
        var msg = document.getElementById("Msg");
        if (notEmpty(algorithmID, language, url)) {
            console.log("Algo name  - " + algorithmID);
            console.log("Language  - " + language);
            console.log("Url  - " + url);
            msg.innerHTML = "Please check your input. The file may not have been selected properly.";
        }
        if (!re.exec(url)) {
            alert("File extension not supported!");
        }
    }
}