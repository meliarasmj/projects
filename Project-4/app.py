import streamlit as st
from chatGPT_API import checkMessage

# Inisialisasi objek ChatGPT
checkMessage_obj = checkMessage()

# Custom CSS
st.markdown("""
    <style>
    .stButton > button {
        display: block;
        margin: 20px auto;
        background-color: #2E8B57;
        color: white;
        font-weight: bold;
    }

    .output {
        background-color: #f2f2f2;
        padding: 1rem;
        border-radius: 8px;
        margin-bottom: 1rem;
        border-left: 6px solid #2E8B57;
    }

    .header {
        text-align: center;
        color: #2E8B57;
    }

    .project-description {
        background-color: #e9f5ec;
        color: #1a1a1a; 
        padding: 15px;
        border-radius: 10px;
        margin-bottom: 30px;
        text-align: justify;
    }
    </style>
""", unsafe_allow_html=True)

# Judul aplikasi
st.markdown("<h1 class='header'>AI-Powered Fact-Checking Tool for Forwarded Messages</h1>", unsafe_allow_html=True)

# Deskripsi singkat proyek
st.markdown("""
<div class='project-description'>
This tool verifies the credibility of forwarded messages commonly shared through messaging platforms. 
It uses AI and natural language processing to provide real-time fact-checking in a simple, user-friendly interface.
</div>
""", unsafe_allow_html=True)

# Tabs
tab1, tab2 = st.tabs(["🔍 Fact Check", "ℹ️ About"])

# Tab utama: Fact Checker
with tab1:
    st.markdown("Paste the message you want to verify below:")
    uploaded_text = st.text_area('Enter message (max 3000 characters)', height=200, max_chars=3000)

    if st.button("Run Fact Check"):
        if uploaded_text.strip() == "":
            st.error("Please enter a message to verify.")
        else:
            with st.spinner('Analyzing message...'):
                response = checkMessage_obj.callChatGPT(uploaded_text)

            st.success("Fact-check complete!")
            points = [x for x in response.split('\n') if x.strip()]
            st.subheader("🧠 AI Analysis:")

            for point in points:
                st.markdown(f"<div class='output'>{point}</div>", unsafe_allow_html=True)

# Tab kedua: Tentang aplikasi
with tab2:
    st.markdown("""
    **Technologies Used**  
    - Python 3.9  
    - OpenAI ChatGPT API  
    - Streamlit  

    This project was developed to help reduce misinformation by allowing users to check the validity of the content they receive via messaging apps.
    """)
