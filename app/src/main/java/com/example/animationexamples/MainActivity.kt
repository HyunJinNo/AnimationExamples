package com.example.animationexamples

import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.animationexamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animation = AnimationUtils.loadAnimation(this, R.anim.animations)
        binding.imageView.startAnimation(animation)

        // 애니메이션의 리스너 설정
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // 애니메이션이 시작될 때의 코드를 작성
            }

            override fun onAnimationEnd(animation: Animation?) {
                // 애니메이션이 종료될 때의 코드를 작성
                binding.imageView.clearAnimation() // 애니메이션 제거
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // 애니메이션이 반복될 때의 코드를 작성
            }
        })

        // 속성 애니메이션을 불러옴.
        AnimatorInflater.loadAnimator(this, R.animator.property_animation).apply {
            // 애니메이션 종료 리스너를 추가
            addListener(object : AnimatorListenerAdapter() {
                // 애니메이션이 종료될 때 애니메이션을 다시 시작
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    start()
                }
            })

            // 속성 애니메이션의 타겟 설정
            setTarget(binding.imageView)

            // 애니메이션 시작
            start()
        }

        AnimatorInflater.loadAnimator(this, R.animator.color_animation).apply {
            // 컬러 애니메이션을 불러오고 ObjectAnimator 클래스로 캐스팅
            val colorAnimator = this@apply as? ObjectAnimator

            // colorAnimator 가 ObjectAnimator 인 경우에만 실행
            colorAnimator?.let {
                // Evaluator 를 ArgbEvaluator() 로 설정
                it.setEvaluator(ArgbEvaluator())

                // 타겟 지정
                it.target = binding.imageView

                // 애니메이션 시작
                start()
            }
        }
    }
}