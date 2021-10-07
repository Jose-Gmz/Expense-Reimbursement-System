function getAllReimbursemnts(){
    let url = 'http://localhost:8000/getAllReimbursements'

    let xhr = new XMLHttpRequest();//ready state 0

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let reimbursements = JSON.parse(xhr.response);
            
            for(let reimbursement of reimbursements){
                
                let new_tr = document.createElement("TR");
                new_tr.setAttribute("id","newTR");
                new_tr.className = "reim_row";
                let x = document.getElementById("reimbursement_table");
                x.appendChild(new_tr);

                let new_claimId = document.createElement('td');
                new_claimId.innerText = reimbursement.claimId;
                
                let new_description = document.createElement('td');
                new_description.innerText = reimbursement.description;

                let new_amount = document.createElement('td');
                new_amount.innerText = reimbursement.amount;

                let new_status = document.createElement('td');
                new_status.innerText = reimbursement.reinbursementStatus;
                   
                let new_pokeId = document.createElement('td');
                new_pokeId.innerText = reimbursement.employee.pokeId;

                new_tr.appendChild(new_claimId);
                new_tr.appendChild(new_description);
                new_tr.appendChild(new_amount);
                new_tr.appendChild(new_status);
                new_tr.appendChild(new_pokeId);

                
                x.appendChild(new_tr);



            }
            
            
            
            console.log(reimbursements);
        }
    }

    xhr.open('GET',url);//ready state 1

    xhr.send(); //ready state 2
}

window.onload = function(){
    this.getAllReimbursemnts();
}