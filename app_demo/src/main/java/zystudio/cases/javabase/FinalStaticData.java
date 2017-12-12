package zystudio.cases.javabase;

public class FinalStaticData {

    private static final String TEXT1 = " CREATE TRIGGER update_note_content_on_delete  AFTER delete ON data WHEN old.mime_type='vnd.android.cursor.item/text_note' BEGIN  UPDATE note   SET snippet=''  WHERE _id=old.note_id; END";

    private static final String TEXT2 = " CREATE TRIGGER update_note_content_on_insert  AFTER INSERT ON data WHEN new.mime_type='vnd.android.cursor.item/text_note' BEGIN  UPDATE note   SET snippet=new.content  WHERE _id=new.note_id; END";

    private static final String TEXT3 = "  CREATE TRIGGER update_note_content_on_update  AFTER UPDATE ON data WHEN old.mime_type='vnd.android.cursor.item/text_note' BEGIN  UPDATE note   SET snippet=new.content  WHERE _id=new.note_id; END";
}
