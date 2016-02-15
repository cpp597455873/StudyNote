  private final int REQUEST_CODE_ASK_CALL_PERMISSIONS = 123;
    Uri uri = Uri.parse("tel:400085085");
    Intent callIntent = new Intent(Intent.ACTION_CALL, uri);

    /**
     * ��绰
     */
    private void call() {
        if (Build.VERSION.SDK_INT < 23) {
            startActivity(callIntent);
        } else {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
            if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_ASK_CALL_PERMISSIONS);
            }
        }
    }


    /**
     * �����绰��Ȩ�޵Ļص�
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    } else {
                        Toast.makeText(ProductDetailActivity.this, "����绰���ִ���", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ProductDetailActivity.this, "�û��ܾ���绰������", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }