#!/bin/bash

for img in *.jpg; do
    convert $img -resize 200x200! $img;
done
