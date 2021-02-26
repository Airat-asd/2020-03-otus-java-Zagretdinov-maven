<script>
    function getUser(url, valueControlLogin) {
    const valueControl = document.getElementById(valueControlLogin);
    const userDataContainer = document.getElementById('userDataContainer');
    const fullUrl = url + (valueControl? (encodeURIComponent(valueControl.value)) : '');
    fetch(fullUrl)
    .then(response => response.json())
    .then(client => userDataContainer.innerHTML = JSON.stringify(client));
}
</script>