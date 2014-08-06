package khaki.test.layout;

import net.simonvt.menudrawer.MenuDrawer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import peanut.khaki.R;

public class MenuLeftOverlayActivity extends Activity {

    private MenuDrawer mDrawer;
    private MenuAdapter mAdapter;
    private ListView mList;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);
        mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY);

        List<Object> items = new ArrayList<Object>();
        items.add(new MenuAdapter.Item("Item 1", R.drawable.ic_action_refresh_dark));
        items.add(new MenuAdapter.Item("Item 2", R.drawable.ic_action_select_all_dark));
        items.add(new MenuAdapter.Category("Cat 1"));
        items.add(new MenuAdapter.Item("Item 3", R.drawable.ic_action_refresh_dark));
        items.add(new MenuAdapter.Item("Item 4", R.drawable.ic_action_select_all_dark));
        items.add(new MenuAdapter.Category("Cat 2"));
        items.add(new MenuAdapter.Item("Item 5", R.drawable.ic_action_refresh_dark));
        items.add(new MenuAdapter.Item("Item 6", R.drawable.ic_action_select_all_dark));
        items.add(new MenuAdapter.Category("Cat 3"));
        items.add(new MenuAdapter.Item("Item 7", R.drawable.ic_action_refresh_dark));
        items.add(new MenuAdapter.Item("Item 8", R.drawable.ic_action_select_all_dark));
        items.add(new MenuAdapter.Category("Cat 4"));
        items.add(new MenuAdapter.Item("Item 9", R.drawable.ic_action_refresh_dark));
        items.add(new MenuAdapter.Item("Item 10", R.drawable.ic_action_select_all_dark));

        mList = new ListView(this);
        mList.setCacheColorHint(0);
        mAdapter = new MenuAdapter(this, items);
        mList.setAdapter(mAdapter);
        mDrawer.setMenuView(mList);

        TextView content = new TextView(this);
        content.setText("This is a sample of an overlayed left drawer.");
        content.setGravity(Gravity.CENTER);
        mDrawer.setContentView(content);
        mDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mDrawer.setDrawerIndicatorEnabled(true);
        mDrawer.peekDrawer(1000, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.toggleMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
