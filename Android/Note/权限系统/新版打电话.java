  private final int REQUEST_CODE_ASK_CALL_PERMISSIONS = 123;
    Uri uri = Uri.parse("tel:400085085");
    Intent callIntent = new Intent(Intent.ACTION_CALL, uri);

    /**
     * 打电话
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
     * 请求打电话的权限的回调
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
                        Toast.makeText(ProductDetailActivity.this, "拨打电话出现错误", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ProductDetailActivity.this, "用户拒绝打电话。。。", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }