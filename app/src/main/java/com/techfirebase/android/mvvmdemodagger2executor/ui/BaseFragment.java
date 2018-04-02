package com.techfirebase.android.mvvmdemodagger2executor.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel>
    extends Fragment {

  private T viewDataBinding;
  private V viewModel;

  private BaseActivity activity;
  private View rootView;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof BaseActivity) {
      BaseActivity baseActivity = (BaseActivity) context;
      this.activity = baseActivity;
      baseActivity.onFragmentAttached();
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    // perform dependency injection before creating the activity
    performDependencyInjection();
    super.onCreate(savedInstanceState);
    viewModel = getViewModel();
    setHasOptionsMenu(false);
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //        return super.onCreateView(inflater, container, savedInstanceState);
    viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
    rootView = viewDataBinding.getRoot();
    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewDataBinding.setVariable(getBindingVariable(), viewModel);
    viewDataBinding.executePendingBindings();
  }

  @Override
  public void onDetach() {
    // manually dereference activity object
    activity = null;
    super.onDetach();
  }

  public BaseActivity getBaseActivity() {
    return activity;
  }

  public T getViewDataBinding() {
    return viewDataBinding;
  }

  /** Below are common utility methods used in every fragment */
  private void performDependencyInjection() {
    AndroidSupportInjection.inject(this);
  }

  /** @return layout resource id created by android */
  public abstract @LayoutRes int getLayoutId();

  /**
   * Override to set binding variable such as ViewModel
   *
   * @return variable id
   */
  public abstract int getBindingVariable();

  /**
   * Override to set View model
   *
   * @return view model instance
   */
  public abstract V getViewModel();

  /** Inner Interface to manage Fragment attachment and detachment in activity */
  public interface Callback {
    void onFragmentAttached();

    void onFragmentDetached(String tag);
  }
}
