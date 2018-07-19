(function (window) {
    // 替换用的数据，使用了4个零宽字符，数据量减少了一半。
    var rep = {
        '00': '\u2000',
        '01': '\u200b',
        '10': '\u200c',
        '11': '\uFEFF'
    };

    var rep_1 = {
        "\u200a": "00",
        "\u200b": "01",
        "\u200c": "10",
        "\uFEFF": "11"
    };

    function hide(str) {
        return str.replace(/[\s\S]/g, function (a) {
            a = a.charCodeAt().toString(2);
            if (a.length < 28) {
                a = Array(29 - a.length).join('0') + a;
            } else if (a.length > 28) {
                console.log(a.length);
            }
            return a.replace(/.{2}/g, function (a) {
                return rep[a];
            });
        });
    }

    function show(str) {
        return str.replace(/.{14}/g, function (a) {
            return String.fromCharCode(parseInt(a.replace(/./g, function (a) {
                return rep_1[a];
            }), 2));
        });
    }

    var kkkkkk = hide("djf空间大师傅给");
    console.log(kkkkkk);
    console.log(show(kkkkkk));
    // console.log('\uff00');
    // for (var i = 2000; i < 20000; i++) {
    //     // var r = (eval("\u" + i)).charCodeAt().toString(10);
    //     // console.log(r);
    //     // var s = r.charCodeAt().toString(16);
    //     // console.log(s);
    // }
})(window);