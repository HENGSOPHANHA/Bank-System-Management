function deleteAccount(accountId) {
    if (confirm('Are you sure you want to delete this account?')) {
        fetch(`/api/accounts/${accountId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Account deleted successfully');
                    window.location.reload(); // Reload the page to update the account list
                } else {
                    alert('Error deleting account');
                }
            })
            .catch(error => {
                console.error('There was an error:', error);
                alert('Error deleting account');
            });
    }
}
