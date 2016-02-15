 function encryptToBase64(utf_8_text) {
        var key = CryptoJS.enc.Utf8.parse('0123456789abcdef');
        var iv = CryptoJS.enc.Utf8.parse('0123456789abcdef');
        var encrypted = CryptoJS.AES.encrypt(
            utf_8_text,
            key, {
                mode: CryptoJS.mode.CBC,
                padding: CryptoJS.pad.Pkcs7,
                iv: iv
            }
        );
        var ciphertext = CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
        ksys.KcpdWriteLog('encryptToBase64:' + ciphertext);
        return ciphertext;

    }

    function decryptFromBase64(ciphertext) {
        var key = CryptoJS.enc.Utf8.parse('0123456789abcdef');
        var iv = CryptoJS.enc.Utf8.parse('0123456789abcdef');
        var decrypted = CryptoJS.AES.decrypt(
            ciphertext,
            key, {
                mode: CryptoJS.mode.CBC,
                padding: CryptoJS.pad.Pkcs7,
                iv: iv
            }
        );
        var decryptedText = decrypted.toString(CryptoJS.enc.Utf8);
        ksys.KcpdWriteLog('encryptToBase64:' + decryptedText);
        return decryptedText;
    }