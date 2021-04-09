let registerflag = document.getElementById('register');
if(registerflag) {
    registerflag.addEventListener('submit', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        var usertype=window.localStorage.getItem("usertype");
        console.log(usertype);
        if (registerflag.checkValidity() === true) {
            let response = await fetch('api/login/registeruser', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    email: document.getElementById('email').value,
                    password: document.getElementById('inputPassword').value,
                    usertype: usertype,
                })
            });
            let result = await response;
            let email = document.getElementById('email').value;
            let password = document.getElementById('inputPassword').value;
            console.log(result);
            if (result['status'] == 200) {
                console.log("In else :" + result['status']);
                window.location = "ThankYou.html";

            }
        }
    });
}
