import React, { useState } from "react";
import { Formik, Form, useField, Field } from "formik";
import axios from "axios";


import './Form.css';
import Upload from '../upload/Upload';

const MyTextInput = ({ label, ...props }) => {
  // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
  // which we can spread on <input> and alse replace ErrorMessage entirely.
  const [field, meta] = useField(props);
  return (
    <>
      <label htmlFor={props.id || props.name}>{label}</label>
      <input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </>
  );
};

const SignupForm = (props) => {
  const [file, setFile] = useState(null);
  const [fileStatus, setfileStatus] = useState(false);
  
  const handleCallbackFromUpload = (fileInput) => {
    console.log("Data From Upload");
    setFile(fileInput)
  }

  const handleOnSubmit = async (values, {resetForm, setSubmitting}) => {
    axios({
      method: "POST",
      url: "http://localhost:8080/htmltopdf",
      data: values
    }).then(response => {
      alert(JSON.stringify(values, null, 2));
      console.log(response.data.taskID);
      props.callbackFromForm();
      resetForm({});
      setfileStatus(!fileStatus);
      setFile(null);
      setSubmitting(false);
    }).catch(err => {
      setSubmitting(false);
      console.log(err)
    });
  };

  return (
    <div>
      <h1>Turn a Picture into a PDF!</h1>
      <Formik
        enableReinitialize={true}
        initialValues={{
          firstName: "",
          lastName: "",
          quote: "",
          file: "",
        }}
        onSubmit={handleOnSubmit}
      >
        {({ values, errors, touched, handleSubmit, setFieldValue , isSubmitting }) => {
          return (
            <Form >
              <div className="input">
                <MyTextInput
                  label="First Name"
                  name="firstName"
                  type="text"
                  placeholder="Jane"
                />
                <MyTextInput
                  label="Last Name"
                  name="lastName"
                  type="text"
                  placeholder="Doe"
                />
                <MyTextInput
                  label="Quote"
                  name="quote"
                  type="text"
                  placeholder="Hello World"
                />
              </div>

              <div className="fileUpload">
                <Field type="hidden" name="file" value={file?.name} />
                <Upload fileStatus={fileStatus} callbackFromUpload={handleCallbackFromUpload}></Upload>
              </div>

              <div>
                <button 
                  type="submit" 
                  disabled={isSubmitting}
                  onClick={() => { file?.URL && setFieldValue("file", `${file?.name}`) }}
                >
                  Submit
                </button>
                <span className= "fileName">{file?.name}</span>
                
              </div>

            </Form>
          )}}
      </Formik>
    </div>
  );
};

export default SignupForm;