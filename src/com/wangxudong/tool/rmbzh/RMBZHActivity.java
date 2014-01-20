package com.wangxudong.tool.rmbzh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RMBZHActivity extends Activity implements OnClickListener,OnKeyListener{
    
	private Button btnTranslate ;
	private TextView txtViewBigNumber;
	private EditText editTextSmallNumber;
	private String  currentNumber="" ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.btnTranslate = (Button) this.findViewById(R.id.zh_btn);
        this.btnTranslate.setOnClickListener(this);
        this.txtViewBigNumber = (TextView) this.findViewById(R.id.big_number);
        this.editTextSmallNumber = (EditText) this.findViewById(R.id.small_number_input);
        this.editTextSmallNumber.setOnKeyListener(this);
    }

	@Override
	public void onClick(View v) {
		if(v.equals(this.btnTranslate)){
			doTranslate();
		}
		
	}

	/**
	 * 开始翻译
	 */
	private void doTranslate() {
		String smallNumber = this.editTextSmallNumber.getText().toString();
		this.currentNumber = smallNumber;
		this.txtViewBigNumber.setText(ChineseMoney.getChineseMoney(smallNumber));
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if(v.equals(this.editTextSmallNumber)){
			if(event.getAction()== KeyEvent.ACTION_DOWN){
				Log.d("wxd test","key down" + keyCode);
				editTextKeyDown(keyCode,event);
			}			
		}
		return false;
	}

	private void editTextKeyDown(int keyCode,KeyEvent event) {
		String newContent = this.editTextSmallNumber.getText().toString();
		if(!this.currentNumber.equals(newContent)){
			//清空输出结果
			this.txtViewBigNumber.setText("");
		}
	}
}