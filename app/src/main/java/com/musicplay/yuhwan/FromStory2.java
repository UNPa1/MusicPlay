package com.musicplay.yuhwan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;

import me.grantland.widget.AutofitHelper;
import me.grantland.widget.AutofitTextView;

public class FromStory2 extends AppCompatActivity {
    String[] MainText = {" 제비는 흥부의 발밑에 박씨를 떨어뜨렸는데 흥부는 이 박씨를 마당에 심었다. 가을이 되자 흥부네 지붕에 커다란 박이 주렁주렁 열렸다. 박을 따 톱질을 하니 금은보화, 비단, 쌀 등이 잔뜩 나와 흥부네는 큰 부자가 되었다. 이 소식을 들은 놀부는 흥부에게 자초지종을 들은 후 자기 집 처마 밑 제비둥지의 아기제비 다리를 일부러 부러뜨리고 다시 치료해 주었다. 이듬해 봄이 되자 제비가 박씨를 물어다 주어 놀부는 이를 마당에 심었다. 가을이 되어 커다란 박이 많이 열려 놀부 부부가 박을 따 톱질을 하였다. 박 안에서는 똥과 더러운 쓰레기들이 나왔으며 다른 박에서는 뿔 달린 도깨비들이 나와 집과 살림살이를 부수고 놀부 부부를 때리고 사라졌다. 거지꼴이 된 놀부는 흥부네 집에 찾아가 엉엉 울며 진심으로 용서를 빌었다. 흥부는 형을 용서하였다. 그 후로 흥부와 놀부는 서로 아껴주며 오랫동안 행복하게 살았다.",
        "옛날 깊은 산골 마을에 아주 게으른 총각이 살았다. 부모님은 아들을 보며 아들의 게으름을 늘 걱정했다. 총각은 예쁘고 아랫마을에서 제일 부지런한 처녀와 혼인을 했다. 색시는 낮에 논밭을 매거나 방아를 찧고 밤에는 베를 짜는 등 매일 일했지만 게으름뱅이는 놀기만 하였다. 보다 못한 색시가 신랑에게 일 좀 하라고 말하자 신랑은 잔소리 하지 말라며 핀잔을 주었고 색시는 울며 밭을 매러 나갔다. 게으름뱅이는 색시가 짜놓은 베를 보고 베를 팔아 놀 생각을 하며 밖을 나섰다. 산등성이에 다다르자 하얀 옷을 입은 노인이 무언가를 만들고 있었다. 게으름뱅이가 무엇을 만드냐고 물어보자 노인은 이것은 소 주둥이에 씌우는 망이라고 했다. 그는 사람이 이 망을 쓰면 평생 일하지 않고 빈둥거리며 살 수 있다고 말했다. 게으름뱅이는 망에 흥미를 보이며 자신이 가져온 베와 망을 바꾸길 원했다.",
        "베를 망으로 바꾼 게으름뱅이는 자신의 입에 주둥이 망을 썼고 그와 동시에 소가 되고 말았다. 노인은 게으름뱅이를 끌고 소시장에 가서 어느 농부에게 게으름뱅이를 팔았다. 노인은 농부에게 소를 팔면서 먹지도 자지도 않으니 일만 시키면 되지만 절대 무는 먹여서는 안 된다고 이야기하였다. 무를 먹으면 소가 죽게 된다는 것이었다. 농부에게 팔려간 게으름뱅이는 채찍을 맞아가며 매일매일 죽도록 일만 하였다. 밤이 되어서야 외양간에서 쉴 수 있었고 게으름뱅이는 색시를 그리워하며 후회의 눈물을 흘렸다. 어느 날 밭을 가는데 무밭이 보여서 이렇게 사느니 죽는 것이 낫다 싶어 무를 뽑아 먹었다. 그러자 입에 있던 주둥이 망이 사라지고 다시 사람으로 돌아왔다. 정신을 차려 보니 노인을 만난 산등성이 위였다. 게으름뱅이는 한달음에 집으로 달려갔고 그 후 부지런히 일하며 색시와 행복하게 살았다.",
        "옛날 금강산 깊은 산골에 나무꾼이 살았다. 그는 늦도록 장가도 못 가고 늙은 어머니를 모시고 살았다. 나무꾼은 나무를 하던 도중 우연히 사냥꾼에게 쫓기는 사슴을 나뭇단 속에 숨겨 목숨을 구해주고, 색시를 얻을 수 있는 방법을 알게 되었다. 나무꾼은 사슴이 시킨 대로 보름달이 뜬 밤 연못에서 선녀들이 목욕을 할 동안 선녀가 벗어 둔 날개옷을 감추었다. 날개옷이 없어 올라가지 못하여 혼자 울고 있는 선녀를 나무꾼이 데려와 함께 살았고 이후 선녀와 혼인을 하게 되었다. 사슴은 아이를 셋 낳을 때까지는 선녀에게 날개옷을 돌려주지 말라고 당부했었지만 나무꾼은 보름달이 뜰 때마다 슬퍼하는 선녀가 안타까워 아이 둘만 낳았음에도 날개옷을 보여주고 만다. 선녀는 날개옷을 보자마자 입고서 두 아이를 데리고 하늘나라로 올라가 버린다.",
        "선녀를 매일 그리워하던 어느 날 사슴이 나타나 보름달 뜨는 날 연못으로 가서 하늘에서 내려온 두레박을 타고 올라가라고 말해 나무꾼에게 다시 한 번 도움을 준다. 하늘에 올라간 나무꾼은 그토록 그리워하던 선녀와 자식들을 만났지만 어머니를 생각하면 마음이 편치 않았다. 나무꾼의 근심어린 모습을 본 선녀는 하늘나라의 말을 내어주며 어머니를 뵙고 오라고 한다. 단, 발이 땅에 닿으면 다시는 돌아올 수 없다고 말해준다. 나무꾼은 천마를 타고 한달음에 어머니에게로 달려갔다. 그런데 말 위에서 어머니가 주신 뜨거운 호박죽을 먹다가 말 등에 흘리는 바람에 말에서 떨어져 영영 하늘나라로 올라갈 수 없게 되었다. 그 후 하늘나라에 있는 선녀와 자식들이 보고 싶어 날마다 하늘을 우러러보던 나무꾼은 죽어서 수탉이 되어 하늘을 보며 운다고 한다.",
        "조선 세종 때 판서 벼슬을 하는 양반 홍문과 노비 출신의 첩 춘섬 사이에서 홍길동이 태어났다. 홍길동은 능력이 출중하고 비범한 아이였으나 아버지를 아버지라 부를 수 없고 형을 형이라 부를 수 없는 서자로서의 자신의 처지를 원통해하였다. 홍 재상에게 초랑이라는 첩이 있었는데 홍길동이 홍 재상에게 예쁨을 받자 이를 시기하여 홍길동 모자를 집 안에서 내쫓기 위해 애를 썼다. 관상을 보는 여자와 계략을 꾸며 안방마님과 맏아들 길현을 찾아가 모함을 하였다. 또한 자객을 시켜 홍길동을 죽이려 했지만 홍길동은 열한 살로 어렸음에도 키가 어른처럼 크고 자유자재로 도술을 부릴 수 있어 자객과 관상 보는 여자를 해치웠다. 신변에 위협을 느낀 홍길동은 부모님께 인사를 드린 후 그 길로 집을 떠났다. 길동이 떠난 후 홍 재상은 초랑이 벌인 일을 알게 되어 초랑을 내쫓는다. 홍길동은 정처 없이 산과 들을 떠돌아다니다 도적들을 발견하였다."};

    private GestureDetectorCompat gestureComp;
    AutofitTextView ET_MainText;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fromstory2);
        gestureComp = new GestureDetectorCompat(this, new FromStory2.ControlGesutre());
        ET_MainText = findViewById(R.id.MainText);
        ET_MainText.setText(MainText[0]);
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.gestureComp.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class ControlGesutre extends GestureDetector.SimpleOnGestureListener {
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                if(count > 0) {
                    count--;
                    ET_MainText.setText(MainText[count]);
                }
            } else if (event2.getX() < event1.getX()) {
                count++;
                if(count != MainText.length) {
                    ET_MainText.setText(MainText[count]);
                }
                else
                {
                    Intent intent = new Intent(FromStory2.this,multi2.class);
                    startActivity(intent);
                    finish();
                }
            }
            return true;
        }
    }
}
