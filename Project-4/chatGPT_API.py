from dotenv import load_dotenv
import os
import json
import openai

load_dotenv()

openai.api_key = os.environ.get('PRIVATE_KEY')

class checkMessage:
    def __init__(self):
        pass

    def convert_sub_objects_to_dict(self, obj):
        if isinstance(obj, list):
            return [self.convert_sub_objects_to_dict(item) for item in obj]
        elif isinstance(obj, dict):
            return {key: self.convert_sub_objects_to_dict(value) for key, value in obj.items()}
        elif hasattr(obj, 'json'):
            return json.loads(obj.json())
        else:
            return obj

    def callChatGPT(self, input_text):
        input_prompt = (
            "You have received the following forwarded message, typically shared on messaging platforms like WhatsApp. "
            "The content is enclosed within triple backticks:\n"
            f"```{input_text}```\n\n"
            "Please fact-check this message. Important instructions:\n"
            "1. For long messages, provide your response in numbered bullet points.\n"
            "2. Do not repeat the original message in your response.\n"
            "3. Keep your answers concise and focused only on factual verification.\n"
            "4. Only provide fact-checking results. Avoid extra commentary.\n"
            '5. At the end, based on your check, respond with either:\n'
            '   - "All facts are correct! The message can be forwarded."\n'
            '   - "WARNING: Please do not forward this message, as it contains incorrect information!"\n\n'
            "Please take your time and ensure the analysis is accurate."
        )

        response_chatGPT_json = openai.completions.create(
            model="gpt-3.5-turbo",
            prompt=input_prompt,
            max_tokens=150
        )

        response_chatGPT_json = self.convert_sub_objects_to_dict(response_chatGPT_json)
        response_content = response_chatGPT_json["choices"][0]["text"]

        return response_content
