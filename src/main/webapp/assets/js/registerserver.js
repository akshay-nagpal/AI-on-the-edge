let registerflag = document.getElementById('register');
if(registerflag) {
    registerflag.addEventListener('submit', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        let usertype=window.localStorage.getItem("usertype");
        // console.log(usertype);
        // console.log(document.getElementById('IP').value);
        // console.log(JSON.stringify({
        //     email: document.getElementById('email').value,
        //     password: document.getElementById('inputPassword').value,
        //     usertype: usertype,
        //     username: document.getElementById('userName').value,
        //     sudo_password: document.getElementById('sudoPassword').value,
        //     IP: document.getElementById('IP').value,
        // }));
        if (registerflag.checkValidity() === true) {
            let response = await fetch('api/login/server', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    email: document.getElementById('email').value,
                    password: document.getElementById('inputPassword').value,
                    username: document.getElementById('userName').value,
                    IP: document.getElementById('IP').value,
                    sudo_password: document.getElementById('sudoPassword').value,
                    usertype: usertype,
                })
            });
            // let result2=await response2;
            let result = await response;
            console.log(result);
            let email = document.getElementById('email').value;
            let password = document.getElementById('inputPassword').value;
            console.log(email);
            if (result['status'] == 200) {
                console.log("In else :" + result['sta   tus']);
                window.location = "ThankYou.html";
            }
            else
            {
                console.log("ni ho paara");
            }
        }
    });
}
