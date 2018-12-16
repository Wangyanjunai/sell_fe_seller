$(function () {
    $('#inputFile').change(function () {
        var fileName = $(this).val()
        alert(fileName)
        $('#inputFile').fileupload({
        })
    })
})