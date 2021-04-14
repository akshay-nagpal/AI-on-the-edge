//Add the file name in the json that was selected
let jobbutton=document.getElementById("runjob")
console.log("Reached job.js");
jobbutton.addEventListener('click', async (e) => {
    alert("clicked");
    let response = await fetch('api/job/schedule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            email: window.localStorage.getItem("email_id"),
            application_name:document.getElementById("application_name").valueOf()
        })
    });

});
function direct(){
    window.location="dash.html"
}


