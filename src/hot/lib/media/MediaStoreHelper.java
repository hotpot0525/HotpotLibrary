package hot.lib.media;

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

	public MediaStoreHelper(Context context) {
		mContext = context;
	}
	
	public ArrayList<Uri> getImageContentUri(){
		ArrayList<Uri> result = new ArrayList<Uri>();
		
		// 画像リストを取得するクエリを発行
		ContentResolver resolver = mContext.getContentResolver();
		String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC";
		Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, sortOrder);
		
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
}
