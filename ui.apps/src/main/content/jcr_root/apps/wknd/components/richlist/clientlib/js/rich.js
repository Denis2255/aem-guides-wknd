$(document).ready(function () {
    $.ajax({
                        type: 'GET',
                      url: '/bin/target-tag',
                      data: {"tag":window.config.tag,
                      "root":window.config.root,
                      "identifier":window.config.id
                      },
                      dataType: "text",
                      success : function(data) {
                      let id = window.config.id;
                            var min = makeList(data,id);
                            console.log(min);
                      },
                      error: function(error) {
                      console.log("An error happened: " + error)}
                    });
});

                    function makeList(data, id) {
                    var main= document.getElementById(id);
                    var fullTag = "";
                    const min = JSON.parse(data);
                    let b = document.getElementById("tagList");
                     min.forEach((elem) => {
                        Object.keys(elem).forEach((el) => {
                            fullTag += el + ": " + elem[el] + " ";
                     })
                     var str = "<h4> " + fullTag + " </h4>";
                     fullTag = "";
                     main.innerHTML = str + main.innerHTML;
                      console.log(elem);
                      })
                    return min;
                  }