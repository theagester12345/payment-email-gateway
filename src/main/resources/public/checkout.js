window.onload = function (){
    var urlParams = new URLSearchParams(window.location.search);
    var id = urlParams.get('id');
    console.log("Id gotten from URL: "+id)
    fetch("http://localhost:8080/pdf/" + id ).then(response => response.json())
        .then(data => {
            console.log(data);
            //set Title
            var title = document.getElementById("title");
            title.textContent = data.title;
            //set category
            var cat = document.getElementById("category");
            cat.textContent = data.category;
            //set price
            var price = document.getElementById("price");
            price.textContent = data.price;
        }). catch(error=> console.error('Error:'+error));

    //make payment button
    var makePaymentButton = document.getElementById('makePayment');
    makePaymentButton.onclick = function() {
        var paymentMethod = document.querySelector("select[name='paymentMethod']").value;
        var cardNumber = document.querySelector("input[name='cardNumber']").value;
        var cardholderName = document.querySelector("input[name='cardholderName']").value;
        var cardholderEmail = document.querySelector("input[name='cardholderEmail']").value;
        var cardDate = document.querySelector("input[name='cardDate']").value;
        var cvv = document.querySelector("input[name='cvv']").value;

        console.log("Payment Method: " + paymentMethod);
        console.log("Card Number: " + cardNumber);
        console.log("Cardholder Name: " + cardholderName);
        console.log("Expiration Date: " + cardDate);
        console.log("Card Holder Email"+ cardholderEmail);
        console.log("CVV: " + cvv);

        var data = {
            paymentMethod: paymentMethod,
            cardNumber: cardNumber,
            cardHolderName: cardholderName,
            cardMonth: cardDate.split("/")[0],
            cardYear: cardDate.split("/")[1],
            cvv: cvv,
            price: price.textContent,
            email:cardholderEmail

        }

        // Make a POST request to your payment endpoint
        fetch('http://localhost:8080/pay', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                if (data.paymentStatusCode == "200"){
                    console.log("Now execute the download of the file with id: "+id+"..." );
                    fetch("http://localhost:8080/download/" + id )
                        .then(response => {
                            if (!response.ok){
                                throw new Error('HTTP error '+ response.status)
                            }
                            return response.blob();
                        }).then(blob => {
                        // You can create a URL for the blob and use it for download
                        let url = URL.createObjectURL(blob);
                        let link = document.createElement('a');
                        link.href = url;
                        link.download = id + '.pdf';
                        link.click();
                    }). catch(error=> console.error('Download Error:'+error));

                }
            })
            .catch((error) => {
                console.error('Payment Error:', error);
            });
    };

    //edit button
    var editOrderButton = document.getElementById('editOrder');
    editOrderButton.onclick = function() {
        window.close();
    };
};
