

document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault(); 
    document.querySelector('.error-message').style.display = 'block'; 
});

const passwordInput = document.getElementById('password');
const showPasswordCheckbox = document.getElementById('showPasswordCheckbox');

showPasswordCheckbox.addEventListener('change', function() {
    if (showPasswordCheckbox.checked) {
        passwordInput.type = 'text';
    } else {
        passwordInput.type = 'password';
    }
});
