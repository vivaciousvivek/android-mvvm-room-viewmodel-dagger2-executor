package com.techfirebase.android.mvvmdemodagger2executor.ui;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
    extends AppCompatActivity implements BaseFragment.Callback {

  private T viewDataBinding;
  private V viewModel;

  /*
   * This is depend on isLoading variable of BaseViewModel, this will used for every activity
   */
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    // perform dependency injection before creating the activity
    performDependencyInjection();
    super.onCreate(savedInstanceState);
    // perform data binding after creating the activity
    performDataBinding();
  }

  private void performDependencyInjection() {
    AndroidInjection.inject(this);
  }

  private void performDataBinding() {
    viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.viewModel = viewModel == null ? getViewModel() : viewModel;
    viewDataBinding.setVariable(getBindingVariable(), viewModel);
    viewDataBinding.executePendingBindings();
  }

  public T getViewDataBinding() {
    return viewDataBinding;
  }

  @Override
  public void onFragmentAttached() {
    // Attach fragment
  }

  @Override
  public void onFragmentDetached(String tag) {
    // Detach fragment
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
}
