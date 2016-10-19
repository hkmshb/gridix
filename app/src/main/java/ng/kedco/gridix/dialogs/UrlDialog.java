package ng.kedco.gridix.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ng.kedco.gridix.R;
import ng.kedco.gridix.SettingsActivity;

/**
 * Created by shaibujnr on 10/18/16.
 */
public class UrlDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String url = getArguments().getString("url",getResources().getString(R.string.default_api_root_url));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.url_dialog,null);
        final EditText input = (EditText) dialogView.findViewById(R.id.url_dialog_input);
        input.setText(url);
        Button done = (Button) dialogView.findViewById(R.id.url_dialog_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity callingActivity = (SettingsActivity) getActivity();
                callingActivity.setUrl(input.getText().toString());
                dismiss();
            }
        });
        builder.setView(dialogView);
        return  builder.create();
    }
}
