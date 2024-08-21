package com.github.gunin_igor75.presentation.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import com.core.common.extension.getParcelableProvider
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.CardChoiceBinding

typealias OnCardChoiceClickListener = () -> Unit

class CardChoiceView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CardChoiceBinding

    private var listener: OnCardChoiceClickListener? = null

    var text: String? = null
        set(value) {
            field = value
            binding.textView.text = value
        }

    var iconTint: Drawable? = null
        set(value) {
            field = value
            binding.imageView.setImageDrawable(value)
        }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        R.style.DefaultCardChoiceViewStyle
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.cardChoiceStyle
    )

    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.card_choice, this, true)
        binding = CardChoiceBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
        initListener()
    }

    fun setListener(onCardChoiceClickListener: OnCardChoiceClickListener) {
        this.listener = onCardChoiceClickListener
    }

    override fun onSaveInstanceState(): Parcelable?     {
        val superState = super.onSaveInstanceState()
            ?: throw IllegalStateException("super.onSaveInstanceState is null")

        val bitmap = iconTint?.toBitmap()
        val bundle = bundleOf(
            TEXT_KEY to text,
            BITMAP_KEY to bitmap,
            SUPER_STATE to superState
        )
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val bundle = state as Bundle
        val saveText = bundle.getString(TEXT_KEY)
        val saveBitmap = bundle.getParcelableProvider<Bitmap>(BITMAP_KEY)
        text = saveText
        iconTint = saveBitmap?.toDrawable(context.resources)
        super.onRestoreInstanceState(bundle.getParcelableProvider(SUPER_STATE))
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CardChoiceView,
            defStyleAttr,
            defStyleRes
        )
        iconTint = typeArray.getDrawable(R.styleable.CardChoiceView_iconCardChoice)
        text = typeArray.getString(R.styleable.CardChoiceView_textCardChoice)
        typeArray.recycle()
    }

    private fun initListener() {
        binding.imageView.setOnClickListener {
            this.listener?.invoke()
        }
    }

//    class SaveState : BaseSavedState {
//
//        var textSave: String? = null
//
//        constructor(superState: Parcelable) : super(superState)
//        constructor(parcel: Parcel) : super(parcel) {
//            textSave = parcel.readString()
//        }
//
//        override fun writeToParcel(out: Parcel, flags: Int) {
//            super.writeToParcel(out, flags)
//            out.writeString(textSave)
//        }
//
//        companion object {
//
//            @JvmField
//            val CREATOR: Parcelable.Creator<SaveState> = object : Parcelable.Creator<SaveState> {
//                override fun createFromParcel(source: Parcel): SaveState {
//                    return SaveState(source)
//                }
//
//                override fun newArray(size: Int): Array<SaveState?> {
//                    return Array(size) { null }
//                }
//            }
//        }
//    }

    companion object {
        private const val BITMAP_KEY = "bitmap_key"
        private const val TEXT_KEY = "text_key"
        private const val SUPER_STATE = "super_state"
    }
}