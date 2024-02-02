document.addEventListener('DOMContentLoaded', function() {
    const createForm = document.getElementById('createAccountForm');
    createForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const accountNumber = document.getElementById('newAccountNumber').value;
        const balance = document.getElementById('newBalance').value;
        createAccount({ accountNumber, balance });
    });

    // ... other code ...
});

function createAccount(accountData) {
    fetch('/api/accounts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(accountData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(newAccount => {
            console.log('Account created:', newAccount);
            // Optionally, add the new account to the accounts table
            // or redirect to the testing page to refresh the list
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

}
