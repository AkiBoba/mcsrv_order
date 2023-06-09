function getDishes() {
    let listOfDishes = [];
    $('input:checkbox:checked').each(function(){
        listOfDishes.push($(this).val());
    });
    console.log(listOfDishes);
    return listOfDishes;
}

$(".new-order").click(function () {
    let dishes = getDishes();
    save(dishes);
})

function save(dishes) {
    console.log("save")
    $.ajax({
        url: '/orders/save',
        type: 'post',
        data: JSON.stringify(dishes),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        complete: function(data){
            console.log(data.status);
            if (data.status === 200) {
                alert(data.responseText);
            }
            else {
                console.log("error");
                alert("Error");
            }
        }
    });
}