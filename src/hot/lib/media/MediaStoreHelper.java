package hot.lib.media;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * メディアストア関係のクラス
 */
public class MediaStoreHelper {

	private Context mContext;
	private ContentResolver mResolver;

	public MediaStoreHelper(Context context) {
		mContext = context;
		 mResolver = mContext.getContentResolver();
	}
	
	public ArrayList<Uri> getImageContentUri(){
		ArrayList<Uri> result = new ArrayList<Uri>();
		
		// 画像リストを取得するクエリを発行
		String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC";
		Cursor cursor = mResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, sortOrder);
		
		try{
			cursor.moveToFirst();
			do{
				int fieldIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
				Long imageId = cursor.getLong(fieldIndex);		
				Uri uri = ContentUris.withAppendedId(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId);
				result.add(uri);
			}while(cursor.moveToNext());
		}finally{
			if(cursor != null) cursor.close();	
		}
		return result;
	}
	
	/**
	 * uriをinputStreamに変換する
	 * @param uri
	 * @return 対象ファイルが存在しない場合はnullを返す
	 */
	public InputStream uriToInputStream(Uri uri){
		try {
			return mResolver.openInputStream(uri);
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
}
