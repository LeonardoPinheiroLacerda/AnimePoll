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

function showExpiredToast(){
    var myToastEl = document.getElementById('toast-expired');
    var myToast = bootstrap.Toast.getOrCreateInstance(myToastEl);
    myToast.show();
}

function vote(){
    
    if(getCookie('anime') == null){
        const vote = async (id) => {
            return fetch(
                `/animes/vote/${id}`,
                {
                    method: "POST",
                }
            );
        }
    
        (async () => {
            const response = await vote(voteId);
            const json = await response.json();

            if(response.status == 200){
                setCookie(json.title);
                showSuccessToast();
            }else{
                showDangerToast();
            }

        })();
    }else{
        showExpiredToast();
    }

    hideModal();

}

function setCookie(title){
    const date = new Date();
    date.setDate(date.getDate() + 1);
    document.cookie = `anime=${title}; expires=${date.toUTCString()}; path=/`;
}


function getCookie(cName) {
    const name = cName + "=";
    const cDecoded = decodeURIComponent(document.cookie); //to be careful
    const cArr = cDecoded.split('; ');
    let res;
    cArr.forEach(val => {
        if (val.indexOf(name) === 0) res = val.substring(name.length);
    })
    return res
}
