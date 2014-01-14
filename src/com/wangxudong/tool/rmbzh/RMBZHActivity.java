package com.wangxudong.tool.rmbzh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RMBZHActivity extends Activity implements OnClickListener{
    
	private Button btnTranslate ;
	private TextView txtViewBigNumber;
	private EditText editTextSmallNumber;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.btnTranslate = (Button) this.findViewById(R.id.zh_btn);
        this.btnTranslate.setOnClickListener(this);
        this.txtViewBigNumber = (TextView) this.findViewById(R.id.big_number);
        this.editTextSmallNumber = (EditText) this.findViewById(R.id.small_number_input);
    }

	@Override
	public void onClick(View v) {
		if(v.equals(this.btnTranslate)){
			doTranslate();
		}
		
	}

	/**
	 * ¿ªÊ¼·­Òë
	 */
	private void doTranslate() {
		String smallNumber = this.editTextSmallNumber.getText().toString();
		this.txtViewBigNumber.setText(ChineseMoney.getChineseMoney(smallNumber));
	}
}