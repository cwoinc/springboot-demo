/**
 * 标题里的“短”字加了引号，只是因为它看起来短，实际并不短，因为字节还是在的，在 unicode 里有一种神奇的字符叫 零宽空白，它的特点是字型的显示宽度为 0，无论堆了多少个零宽字符，你都看不见它。

 就像上面我写的例子中，Function("<这里>".repla... 藏了大量的零宽字符，实际看起来就好像是一个空字符串 “”，这个“空”字串即是 md5 的函数定义经过编码转换后得到的全零宽字符串，此创意最初源自一个叫z.js 的库。

 每个字符都有一个唯一的编码，将编码以 2 进制表示得到 01.. 的字串，把 1 替换成 U+200C，把 0 替换成 U+200D 就得到一个全零宽空白的字符串，每 8 位零宽字符可用于表示 1 个 ascii字符，所以例子当中，理论上是变长的，不算解码程序的 129 个字符，仅空白就占了原文 8 倍的体积，如果出现中文，那就更不止了，因为中文已经超过了 ascii 的范围，需要先转成纯 ascii （如以 \uxxxx 表示）后再处理。

 在 unicode 里，至少有 U+200B, U+200C, U+200D 和 U+FEFF 四个零宽字符，如果把这 4 个字符全用上，上面的例子又可以减少 1 半的体积
 */

(function (window) {
    var rep = { // 替换用的数据，使用了4个零宽字符，数据量减少了一半。
        '00': '\u200b',
        '01': '\u200c',
        '10': '\u200d',
        '11': '\uFEFF'
    };

    var rep_1 = {"\u200b": "00", "\u200c": "01", "\u200d": "10", "\uFEFF": "11"};

    function hide(str) {
        str = str.replace(/[^\x00-\xff]/g, function (a) { // 转码 Latin-1 编码以外的字符。
            return escape(a).replace('%', '\\');
        });

        str = str.replace(/[\s\S]/g, function (a) { // 处理二进制数据并且进行数据替换
            a = a.charCodeAt().toString(2);
            a = a.length < 8 ? Array(9 - a.length).join('0') + a : a;
            return a.replace(/../g, function (a) {
                return rep[a];
            });
        });
        return str;
    }

    function show(str) {
        return str.replace(/.{4}/g, function (a) {
            return String.fromCharCode(parseInt(a.replace(/./g, function (a) {
                return rep_1[a];
            }), 2));
        });
    }

    var tpl = '("@code".replace(/.{4}/g,function(a){var rep={"\u200b":"00","\u200c":"01","\u200d":"10","\uFEFF":"11"};return String.fromCharCode(parseInt(a.replace(/./g, function(a) {return rep[a]}),2))}))';

    window.hider = function (code, type) {
        var str = hide(code); // 生成零宽字符串

        console.log(code, code.length);
        console.log(str, str.length);
        eval(show(str));

        str = tpl.replace('@code', str); // 生成模版
        if (type === 'eval') {
            str = 'eval' + str;
        } else {
            str = 'Function' + str + '()';
        }

        return str;
    }
})(window);

