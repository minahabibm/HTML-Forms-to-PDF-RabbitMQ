import React, { useState } from "react";
import { Formik, Form, useField, useFormikContext } from "formik";

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

const SignupForm = () => {
  const [file, setFile] = useState(null);

  const handleCallbackFromUpload = (fileInput) => {
    console.log("Data From Upload");
    setFile(fileInput)
  }

  return (
    <>
      <h1>Turn a Picture into a PDF!</h1>
      <Formik
        enableReinitialize={true}
        initialValues={{
          firstName: "",
          lastName: "",
          quote: "",
          file: file?.URL ,
        }}
        onSubmit={ values => {alert(JSON.stringify(values, null, 2));}
        //   async (values, { setSubmitting }) => {
        //   await new Promise(r => setTimeout(r, 500));
        //   setSubmitting(false);
          
        // }
      }
      >
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
            <Upload callbackFromUpload = {handleCallbackFromUpload}></Upload>
          </div>

          <div>
            <button type="submit">Submit</button>
            <span className= "fileName">{file?.name}</span>
            
          </div>

        </Form>
      </Formik>


    </>
  );
};

export default SignupForm;