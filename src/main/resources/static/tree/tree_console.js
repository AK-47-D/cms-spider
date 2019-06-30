$(function () {
    let category = document.getElementById('category').value;
    let jsonEditor = new JSONEditor(document.getElementById('json_editor'), options);

    $.ajax({
        url: `/api/tree.json?category=${category}`,
        type: 'GET',
        success: (data) => {
            jsonEditor.set(data.data);
        },
        error: (err) => {
            Alert("", JSON.stringify(err), "error")
        }
    });


    $('#save-json-btn').on('click', () => {
        let data = jsonEditor.get();
        $.ajax({
            url: `/api/saveTree.json`,
            type: 'POST',
            data: {
                category: category,
                data: JSON.stringify(data)
            },
            success: (data) => {
                if (data.sucess = true) {
                    location.reload()
                } else {
                    Alert("", data.message, "error")
                }
            },
            error: (err) => {
                Alert("", JSON.stringify(err), "error")
            }
        });
    })

});


const options = {
    mode: 'tree',
    modes: ['text', 'tree', 'view', 'code', 'form'], // allowed modes
    onError: function (err) {
        alert(err.toString());
    },
    onModeChange: function (newMode, oldMode) {
        console.log('Mode switched from', oldMode, 'to', newMode);
    }
};


function Alert(title, msg, type) {
    new PNotify({
        title: title,
        styling: 'bootstrap3',
        text: msg,
        type: type,
        delay: 3000
    });
}