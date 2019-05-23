package com.fanikiosoftware.mynews.Controllers.Fragments;

import com.fanikiosoftware.mynews.R;

//import butterknife.BindView;
import icepick.State;

public class TopFragment extends BlankFragment {

//    @BindView(R.id.fragment_detail_text_view) TextView textView;
    @State int buttonTag;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BlankFragment newInstance() {
        return new TopFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void configureDesign() {
    }

    @Override
    protected void updateDesign() {
        //  this.updateTextView(this.buttonTag);
    }
}