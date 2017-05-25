package com.rj.lk.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.rj.lk.R;
import com.rj.lk.activity.ReportActivity;

public class Fragment3 extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm3, container, false);

        RelativeLayout rlContact = (RelativeLayout) view
                .findViewById(R.id.rl_service_contact);
        RelativeLayout rlReport = (RelativeLayout) view
                .findViewById(R.id.rl_service_report);

        rlContact.setOnClickListener(this);
        rlReport.setOnClickListener(this);
        return view;
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_service_contact:
                onClickContact();
                break;
            case R.id.rl_service_report:
                Intent intent = new Intent(v.getContext().getApplicationContext(), ReportActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void onClickContact() {
        final String phoneDomestic = getString(R.string.phone_service_domestic);
        final String phoneForeign = getString(R.string.phone_service_foreign);
        final String str1 = getString(R.string.phone_service_ben);
        final String str2 = getString(R.string.phone_service_xiao);
        final String str3 = getString(R.string.phone_service_ke);

        AlertDialog.Builder builder = new Builder(getActivity());
        builder.setTitle(str3);

        final String items[] = {str1 + phoneDomestic,
                str2 + phoneForeign};
        builder.setSingleChoiceItems(items, -1,
                new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = null;

                        if (which == 0) {
                            number = phoneDomestic;
                        } else {
                            number = phoneForeign;
                        }
                        //拨打电话
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri
                                .parse("tel:" + number));
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("tell", new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.show();
    }
}
