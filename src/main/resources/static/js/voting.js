var voteId;

function showModal(id){
    voteId = id;
    const modal = getModal();
    modal.show();
}

function hideModal(){
    const modal = getModal();
    modal.hide();
}

function getModal(){
    const myModalEl = document.querySelector('#confirmation-modal');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    return modal;
}

function showSuccessToast(){
    var myToastEl = document.getElementById('toast-success');
    var myToast = bootstrap.Toast.getOrCreateInstance(myToastEl);
    myToast.show();
}

function showDangerToast(){
    var myToastEl = document.getElementById('toast-danger');
    var myToast = bootstrap.Toast.getOrCreateInstance(myToastEl);
    myToast.show();
}

function showExpiredToast(message){
    document.querySelector('#expired-toast-body').innerHTML = message;

    var myToastEl = document.getElementById('toast-expired');
    var myToast = bootstrap.Toast.getOrCreateInstance(myToastEl);
    myToast.show();
}

function vote(){
    
    const CSRF = document.getElementsByName('_csrf')[0].value
    const vote = async (id) => { 
        return fetch(
            `/animes/vote/${id}`,
            {
                method: "POST",
                headers: {
                    'X-XSRF-TOKEN': CSRF
                },
            }
        );
    }

    (async () => {
        const response = await vote(voteId);
       
        if(response.status == 200){
            showSuccessToast();

        }else if(response.status == 400){
            const json = await response.json();


            showExpiredToast(json.message);
        }else{
            showDangerToast();
        }

    })();


    hideModal();

}
