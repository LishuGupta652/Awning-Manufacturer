package com.example.awningmanufacturer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<VideoItem> videoItems;
    private Context context;

    public VideoAdapter(List<VideoItem> videoItems, Context context) {
        this.videoItems = videoItems;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videos_list_item, viewGroup, false);

        return new VideoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        VideoItem videoItem = videoItems.get(i);

        viewHolder.tvTitle.setText(videoItem.getTitle());

        String playVideo= "<html><iframe type=\"text/html\"  src=\"https://www.youtube.com/embed/" + videoItem.getUrl() + "\"  frameborder=\"0\"></body></html>";

        playVideo = "<iframe class=\"youtube-player\" " + "style=\"border: 0; width: 100%;"
                + "padding:0px; margin:0px\" " + "id=\"ytplayer\" type=\"text/html\" "
                + "src=\"http://www.youtube.com/embed/" + videoItem.getUrl()
                + "?&theme=dark&autohide=2&modestbranding=1&showinfo=0&autoplay=1\fs=0\" "
                + "allowfullscreen autobuffer " + "controls onclick=\"this.play()\">\n" + "</iframe>\n";

        viewHolder.webViewVideo.loadData(playVideo, "text/html", "utf-8");
    }


    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitle;
        public WebView webViewVideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            webViewVideo = itemView.findViewById(R.id.webViewVideo);
            webViewVideo.getSettings().setJavaScriptEnabled(true);
        }
    }
}
