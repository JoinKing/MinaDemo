package mina.king.com.minademo.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import mina.king.com.minachat.contract.ChatScreenContract;
import mina.king.com.minachat.presenter.ChatScreenPresenter;
import mina.king.com.minademo.R;
import mina.king.com.minademo.base.BaseFragment;
import mina.king.com.minachat.beans.MessageInfo;
import mina.king.com.minademo.ui.activity.ContactActivity;
import mina.king.com.minachat.utils.Constants;
import mina.king.com.minademo.util.FileUtils;
import mina.king.com.minademo.util.PhotoUtils;


/**
 * Created by king
 * @date 2018.11.14
 */

public class ChatFunctionFragment extends BaseFragment {
    private static final String TAG = "ChatFunctionFragment";
    private View rootView;
    private static final int CODE_TAKE_PHOTO = 0x111;
    private static final int CODE_CROP_PHOTO = 0xa2;
    private static final int REQUEST_CODE_PICK_IMAGE = 0xa3;
    private static final int REQUEST_CODE_PICK_FILE = 0xa4;
    private static final int CODE_REQUEST_CAMERA = 0xa5;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0xa6;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE = 0xa7;
    private static final int MY_PERMISSIONS_REQUEST_CAMERACODE = 0xa8;

    private int output_X = 480;
    private int output_Y = 480;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    TextView tvCapture, tvAlbum, tvContact, tvCloud, tvFile, tvLocation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_chat_function, container, false);
            findViewByIds(rootView);
            setItemClick();
        }
        return rootView;
    }

    private void findViewByIds(View rootView) {
        tvCapture = (TextView) rootView.findViewById(R.id.chat_function_capture);
        tvAlbum = (TextView) rootView.findViewById(R.id.chat_function_album);
        tvContact = (TextView) rootView.findViewById(R.id.chat_function_contact);
        tvCloud = (TextView) rootView.findViewById(R.id.chat_function_cloud);
        tvFile = (TextView) rootView.findViewById(R.id.chat_function_file);
        tvLocation = (TextView) rootView.findViewById(R.id.chat_function_location);
    }

    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA)) {
                Toast.makeText(mActivity, "您已拒绝过一次", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERACODE);
        } else {//有权限直接调用系统相机拍照
            imageUri = Uri.fromFile(fileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                imageUri = FileProvider.getUriForFile(mActivity, Constants.AUTHORITY, fileUri);//通过FileProvider创建一个content类型的Uri
            PhotoUtils.takePicture(this, imageUri, CODE_TAKE_PHOTO);
        }
    }

    public void setItemClick() {
        tvCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoObtainCameraPermission();
            }
        });
        tvAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE);

                } else {
                    choosePhoto();
                }
            }
        });

        tvFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE);
                } else {
                    chooseFile();
                }
            }
        });

        tvCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContact();
            }
        });
    }

    private void showContact() {
        Intent intent = new Intent(mActivity, ContactActivity.class);
        startActivity(intent);
    }

    private void chooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_PICK_FILE);
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        imageUri = Uri.fromFile(fileUri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(mActivity, Constants.AUTHORITY, fileUri);
            PhotoUtils.takePicture(this, imageUri, CODE_TAKE_PHOTO);
        }
    }

    /**
     * 从相册选取图片
     */
    private void choosePhoto() {
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);

    }

    public void onActivityResult(int req, int res, Intent data) {
        switch (req) {
            case CODE_TAKE_PHOTO:
                if (res == Activity.RESULT_OK) {//相机路径
                    MessageInfo messageInfo = new MessageInfo();
                    messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
                    messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                    messageInfo.setFilepath(fileUri.getAbsolutePath());
                    messageInfo.setFileType(Constants.CHAT_FILE_TYPE_IMAGE);

                    File file = new File(messageInfo.getFilepath());
                    EventBus.getDefault().post(messageInfo);//发送图片

                    if (callback!=null){
                        callback.getFile(file);
                    }

                }

                break;
            case CODE_CROP_PHOTO:
                if (res == Activity.RESULT_OK) {
                    try {
                        MessageInfo messageInfo = new MessageInfo();
                        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
                        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                        messageInfo.setFilepath(cropImageUri.getPath());
                        messageInfo.setFileType(Constants.CHAT_FILE_TYPE_IMAGE);
                        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
                        EventBus.getDefault().post(messageInfo);
                    } catch (Exception e) {

                    }
                } else {
                    Log.d(Constants.TAG, "失败");
                }

                break;
            case REQUEST_CODE_PICK_IMAGE:
                if (res == Activity.RESULT_OK) {//相册路径
                    try {
                        Uri uri = data.getData();
                        MessageInfo messageInfo = new MessageInfo();
                        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
                        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                        messageInfo.setFilepath(getImageRealPathFromURI(uri));
                        messageInfo.setFileType(Constants.CHAT_FILE_TYPE_IMAGE);
                        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
                        EventBus.getDefault().post(messageInfo);
                        File file = new File(messageInfo.getFilepath());
                        if (callback!=null){
                            callback.getFile(file);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(Constants.TAG, e.getMessage());
                    }
                } else {
                    Log.d(Constants.TAG, "失败");
                }

                break;
            case REQUEST_CODE_PICK_FILE:
                if (res == Activity.RESULT_OK) {
                    try {
                        Uri uri = data.getData();
                        Log.e(TAG, "onActivityResult: ->" + uri.getPath());
                        MessageInfo messageInfo = new MessageInfo();
                        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
                        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                        messageInfo.setFilepath(FileUtils.getFileAbsolutePath(mActivity, uri));
                        messageInfo.setFileType(Constants.CHAT_FILE_TYPE_FILE);
                        EventBus.getDefault().post(messageInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(Constants.TAG, e.getMessage());
                    }
                } else {
                    Log.d(Constants.TAG, "失败");
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    toastShow("请同意系统权限后继续");
                }
                break;
            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choosePhoto();
                } else {
                    toastShow("请同意系统权限后继续");
                }
                break;
            case MY_PERMISSIONS_REQUEST_CAMERACODE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    imageUri = Uri.fromFile(fileUri);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        PhotoUtils.takePicture(this, imageUri, CODE_CROP_PHOTO);
                    }
                }
                break;
        }
    }

    public String getImageRealPathFromURI(Uri contentUri) {

        //TODO upload file、image、voice then return url;
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private imageCallback callback;

    public void setCallback(imageCallback callback) {
        this.callback = callback;
    }

    public interface imageCallback{
        void getFile(File file);
    }
}
