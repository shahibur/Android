package com.example.lenovo.chathomes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Lenovo on 2/20/2018.
 */

class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                requestsFragment RequestsFragment=new requestsFragment();
                return RequestsFragment;

            case 1:
                chatsFragment ChatsFragment=new chatsFragment();
                return  ChatsFragment;
            case 2:
                friendsFragment FriendsFragment=new friendsFragment();
                return FriendsFragment;
            default:
                return null;

        }

    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Requests";
            case 1:
                return "Chats";
            case 2:
                return "Friends";
            default:
                return null;
        }
    }
}
