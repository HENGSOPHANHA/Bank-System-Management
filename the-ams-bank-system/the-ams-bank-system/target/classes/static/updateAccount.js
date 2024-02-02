document.addEventListener('DOMContentLoaded', function() {
    const updateForm = document.getElementById('updateAccountForm');
    updateForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const accountId = document.getElementById('accountId').value;
        const accountNumber = document.getElementById('accountNumber').value;
        const balance = document.getElementById('balance').value;
        updateAccount(accountId, { accountNumber, balance });
    });
});

function updateAccount(accountId, accountData) {
    fetch(`/api/accounts/${accountId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(accountData)
    })
        .then(response => response.json())
        .then(updatedAccount => {
            console.log('Account updated:', updatedAccount);
            // Redirect or update UI as needed
        })
        .catch(error => console.error('There was an error:', error));
}
